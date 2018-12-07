const rxjs = require('rxjs')

rxjs.of(1,2,3).subscribe(
    (i) => console.log(i), 
    (err) => console.log(err), 
    () => console.log('end'))