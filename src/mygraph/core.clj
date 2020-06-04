(ns mygraph.core
  (:gen-class))

(declare create-corona-chart-sample-call)

(require '[com.hypirion.clj-xchart :as c])
(require '[cheshire.core :refer :all])
(import '(java.io ByteArrayInputStream))



(defn -main
  [& args]
  (create-corona-chart-sample-call))

(defn read-covid-https-data
  []
  (parse-stream (clojure.java.io/reader "https://pomber.github.io/covid19/timeseries.json")))

(defn find-country-data
  [country]
  "bla"
  (get (read-covid-https-data) country))

(defn find-property
  [country slot]
  (for [x (find-country-data country)] (get x slot)))

(defn create-corona-chart
  [country property]
  (c/xy-chart {(str country "-" property)
               [(range (count (find-property country property)))
                (vec (find-property country property))]}))


(defn create-corona-chart-nz
  []
  (c/spit (create-corona-chart "New Zealand" "confirmed") "nz.jpg"))

(defn create-corona-chart-sweden
  []
  (c/spit (create-corona-chart "Sweden" "confirmed") "sweden.jpg"))


(create-corona-chart-sweden )
(create-corona-chart-nz)



