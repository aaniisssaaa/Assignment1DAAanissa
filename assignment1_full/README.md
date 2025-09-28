# Assignment 1 — DAA1 (Complete) — README.md

**Содержит:** реализации MergeSort, QuickSort, Deterministic Select (MoM5), Closest Pair (2D), метрики, CLI, тесты и примеры запуска.

## Как собрать и запустить
1. Сборка (Maven предполагается):
```
# в IntelliJ импортировать как Maven project или выполнить сборку вручную
mvn clean package
```
2. Пример запуска CLI (генерирует CSV):
```
java -jar target/assignment1.jar --algo mergesort --n 100000 --runs 3 --out metrics.csv
```

## Реализовано (кратко)
- MergeSort: reusable buffer, cutoff на 32, insertion sort.
- QuickSort: randomized pivot, recurse on smaller partition, loop on larger.
- DeterministicSelect: median-of-medians (groups of 5), linear time.
- ClosestPair: divide-and-conquer O(n log n), strip check up to 7 neighbors.

## Метрики и CSV
CSV колонки: algorithm,n,run_id,time_ms,max_depth,comparisons,assignments

## Тесты
JUnit5 тесты для каждой реализации: корректность и сравнение с Arrays.sort / brute-force.

## Краткий анализ рекуррент
- MergeSort: T(n)=2T(n/2)+Θ(n) ⇒ Θ(n log n) (Master case 2).
- QuickSort (avg): Θ(n log n), worst Θ(n^2) for bad pivots.
- DeterministicSelect: T(n)=T(n/5)+T(7n/10)+Θ(n) ⇒ Θ(n) (Akra–Bazzi / standard proof).
- Closest Pair: T(n)=2T(n/2)+Θ(n) ⇒ Θ(n log n).

## Git workflow (должно быть в репо при сдаче)
Ветки: main, feature/mergesort, feature/quicksort, feature/select, feature/closest, feature/metrics.
Коммиты: init, feat(metrics), feat(mergesort), feat(quicksort), refactor(util), feat(select), feat(closest), feat(cli), bench(jmh), docs(report), fix, release:v1.0

