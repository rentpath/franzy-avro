(defproject com.rentpath/franzy-avro "0.0.2"
  :description "(fork of franzy-avro) A Kafka Serializer/Deserializer supporting Avro, and an add-on for Franzy, a Clojure Kafka suite of libraries."
  :url "https://github.com/rentpath/franzy-avro"
  :author "rentpath, llc"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :deploy-repositories [["releases" {:url "https://clojars.org/repo/"
                                     :username [:gpg :env/CLOJARS_USERNAME]
                                     :password [:gpg :env/CLOJARS_PASSWORD]
                                     :sign-releases false}]]
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.apache.kafka/kafka-clients "0.10.1.1"]
                 [com.damballa/abracad "0.4.13"]]
  :plugins [[lein-codox "0.9.4"]]
  :codox {:metadata    {:doc/format :markdown}
          :doc-paths   ["README.md"]
          :output-path "doc/api"}
  :profiles {:dev              {:dependencies [[midje "1.7.0"]]
                                :plugins      [[lein-midje "3.2"]
                                               [lein-set-version "0.4.1"]
                                               [lein-update-dependency "0.1.2"]
                                               [lein-pprint "1.1.1"]]}
             :reflection-check {:global-vars
                                {*warn-on-reflection* true
                                 *assert*             false
                                 *unchecked-math*     :warn-on-boxed}}})
