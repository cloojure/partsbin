(ns partsbin.examples.example
  (:require [partsbin.core :refer [create start stop restart system reset-config!]]
            [partsbin.datomic.api.core :as datomic]
            [partsbin.hawk.core.core :as hawk]
            [partsbin.immutant.web.core :as web]
            [partsbin.clojure.java.jdbc.core :as jdbc]
            [partsbin.datascript.core.core :as ds]
            [clojure.java.jdbc :as j]
            [integrant.core :as ig]
            [datascript.core :as d]
            [clojure.edn :as edn]
            [clojure.pprint :as pp]
            [clojure.string :as cs])
  (:import (java.io File)))

(defn app [{:keys [sql-conn dsdb] :as request}]
  (let [res (j/query sql-conn "SELECT 1")
        names (d/q
                '[:find [?name ...]
                  :in $
                  :where
                  [_ :name ?name]]
                @dsdb)]
    {:status 200
     :body   (str "OK - " (into [] res) " - " names)}))

(defn file-handler [{:keys [dsdb] :as ctx} {:keys [^File file kind] :as event}]
  (when (and (= kind :modify)
             (.exists file)
             (.isFile file)
             (cs/ends-with? (.getName file) ".edn"))
    (let [data (edn/read-string (slurp file))]
      (println "Transacting data.")
      (d/transact! dsdb data)))
  ctx)

(def config
  {::jdbc/connection    {:connection-uri "jdbc:h2:mem:mem_only"}
   ::datomic/database   {:db-uri  "datomic:mem://example"
                         :delete? true}
   ::ds/connection      {:name   {:db/unique :db.unique/identity}
                         :powers {:db/cardinality :db.cardinality/many}}
   ::datomic/connection {:database (ig/ref ::datomic/database)
                         :db-uri   "datomic:mem://example"}
   ::hawk/watch         {:groups [{:paths   ["example"]
                                   :handler #'file-handler}]
                         :dsdb   (ig/ref ::ds/connection)}
   ::web/server         {:host         "0.0.0.0"
                         :port         3000
                         :sql-conn     (ig/ref ::jdbc/connection)
                         :datomic-conn (ig/ref ::datomic/connection)
                         :dsdb         (ig/ref ::ds/connection)
                         :handler      #'app}})

(defonce sys (create config))

(comment
  (let [{:keys [::jdbc/connection]} (system sys)]
    (j/query connection "SELECT 1")))