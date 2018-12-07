const rxjs = require('rxjs')

const observer = {
    next: (i) => console.log('new value: ' + i),
    error: (err) => console.log('error: ' + err),
    complete: () => console.log('completed')
}

const {map} = require('rxjs/operators')

// Syntax error for rxjs
// rxjs.of(1,2,3).map( .... 

rxjs.of(1,2,3).pipe(
    map(i => {
        if (i == 2) {
            throw new Error('boom')
        } else {
            return i
        }
    })
).subscribe(observer)

