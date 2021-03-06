(defproject markbastian/partsbin "0.1.3-SNAPSHOT"
  :description "A project for creating functional, data-driven systems."
  :url "https://github.com/markbastian/partsbin"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  :exclusions [clj-time
               commons-codec
               commons-fileupload
               commons-io
               joda-time
               primitive-math
               com.google.code.findbugs/jsr305
               com.google.guava/guava
               com.taoensso/encore
               org.clojure/tools.logging
               org.clojure/tools.reader
               org.jboss.logging/jboss-logging
               org.slf4j/slf4j-api
               ring/ring-codec
               ring/ring-core]

  :dependencies [[org.clojure/clojure "1.10.1"]
                 [integrant "0.7.0"]
                 [com.taoensso/timbre "4.10.0"]
                 [datascript "0.18.7" :scope "provided"]
                 [etaoin "0.3.6" :scope "provided"]
                 [hawk "0.2.11" :scope "provided"]
                 [hikari-cp "2.9.0" :scope "provided"]
                 [juxt/crux-core "19.09-1.5.0-alpha" :scope "provided"]
                 [clojure.jdbc/clojure.jdbc-c3p0 "0.3.3" :scope "provided"]
                 [com.datomic/datomic-free "0.9.5697" :scope "provided"]
                 [com.h2database/h2 "1.4.200" :scope "provided"]
                 [com.novemberain/monger "3.5.0" :scope "provided"]
                 [factual/durable-queue "0.1.6" :scope "provided"]
                 [org.clojure/java.jdbc "0.7.10" :scope "provided"]
                 [org.immutant/scheduling "2.1.10" :scope "provided"]
                 [org.immutant/web "2.1.10" :scope "provided"]
                 [ring/ring-jetty-adapter "1.8.0" :scope "provided"]
                 [seancorfield/next.jdbc "1.0.10" :scope "provided"]
                 ;Just to prevent version conflicts
                 [org.clojure/tools.reader "1.3.2" :scope "provided"]
                 [clj-time "0.15.2" :scope "provided"]
                 [commons-codec "1.13" :scope "provided"]
                 [commons-fileupload "1.4" :scope "provided"]
                 [commons-io "2.6" :scope "provided"]
                 [joda-time "2.10.5" :scope "provided"]
                 [primitive-math "0.1.6" :scope "provided"]
                 [com.google.code.findbugs/jsr305 "3.0.2" :scope "proivided"]
                 [com.google.guava/guava "23.0" :scope "provided"]
                 [com.taoensso/encore "2.116.0"]
                 [org.clojure/tools.logging "0.5.0" :scope "provided"]
                 [org.jboss.logging/jboss-logging "3.4.1.Final" :scope "provided"]
                 [org.slf4j/slf4j-api "1.7.29" :scope "provided"]
                 [ring/ring-codec "1.1.2" :scope "provided"]
                 [ring/ring-core "1.8.0" :scope "provided"]]

  ;Note that these instructions may need to be followed to release on OS X.
  ;https://stackoverflow.com/questions/39494631/gpg-failed-to-sign-the-data-fatal-failed-to-write-commit-object-git-2-10-0
  :repositories [["releases" {:url "https://repo.clojars.org" :creds :gpg}]
                 ["snapshots" {:url "https://repo.clojars.org" :creds :gpg}]])
