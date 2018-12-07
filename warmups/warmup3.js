const rxjs = require('rxjs')

const observer = {
    next: (i) => console.log('new value: ' + i),
    error: (err) => console.log('error: ' + err),
    complete: () => console.log('completed')
}

rxjs.of(1,2,3).subscribe(observer)
