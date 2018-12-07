const rxjs = require('rxjs')

const observer = {
    next: (i) => console.log('new value: ' + i),
    error: (err) => console.log('error: ' + err),
    complete: () => console.log('completed')
}

const {map, mergeAll, filter, mapTo, take} = require('rxjs/operators')

const makeInnerObservable = (i) => rxjs
    .interval(300)
    .pipe(
        mapTo(i),
        take(8 - i))

const outerObservable = rxjs
    .interval(200)
    .pipe(
        filter(i => i % 2 === 0), // [0, 2, 4, 6, 8, ...]
        take(4), // [0, 2, 4, 6]
        map(makeInnerObservable), // [[0,0,0,0,0,0,0,0], [2,2,2,2,2,2], [4,4,4,4], [6,6]]
        mergeAll() // ?
    )

outerObservable.subscribe(observer)