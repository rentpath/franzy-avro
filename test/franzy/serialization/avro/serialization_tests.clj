(ns franzy.serialization.avro.serialization-tests
  (:require [midje.sweet :refer :all]
            [franzy.serialization.avro.serializers :as serializers]
            [franzy.serialization.avro.deserializers :as deserializers]
            [abracad.avro :as avro]
            [clojure.java.io :as io]))

(fact
  "Avro can serialize EDN"
  (let [serializer (serializers/avro-serializer)
        deserializer (deserializers/avro-deserializer)
        data {:chicken "sometimes" :good-with-rice true :orders 57}]
    (->> data
         (.serialize serializer "leftovers")
         (.deserialize deserializer "leftovers")) => data))

(fact
 "Avro can serialize a custom schema."
 (let [schema (avro/parse-schema
               {:name "example", :type "record",
                :fields [{:name "left", :type "string"}
                         {:name "right", :type "long"}]
                :abracad.reader "vector"})
       serializer (serializers/avro-serializer schema)
       deserializer (deserializers/avro-deserializer schema)
       data  ["foo" 31337]]
   (->> data
        (.serialize serializer "leftovers")
        (.deserialize deserializer "leftovers")) => data))

(fact
 "Avro can serialize clickstream schema."
 (let [schema (avro/parse-schema (slurp (str (.getCanonicalPath (clojure.java.io/file "."))
                                             "/test/franzy/serialization/avro/clickstream_schema.avsc")))
       serializer (serializers/avro-serializer schema)
       deserializer (deserializers/avro-deserializer schema)
       data {:profile "rent",
             :subprofile "something"
             :visitor "1000",
             :visit "1000"
             :page "/georgia/atlanta/apartments__condos_houses_townhouses",
             :action "pageview",
             :selection "email",
             :seq "srp"}]
   (->> data
        (.serialize serializer "clickstream")
        (.deserialize deserializer "clickstream")) => data))
