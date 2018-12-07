const rxjs = require('rxjs')

const observer = {
    next: (i) => console.log('new value: ' + i),
    error: (err) => console.log('error: ' + err),
    complete: () => console.log('completed')
}

const subject = new rxjs.Subject();
subject.subscribe(observer)

subject.next('hi')
subject.next('yo')
subject.complete()
subject.next('wazzup')
