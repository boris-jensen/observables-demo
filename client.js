const http = require('http')
const rxjs = require('rxjs')
const {concatMap, delay} = require('rxjs/operators')
const querystring = require('querystring');

const sendBump = (i) => {
    const postData = querystring.stringify({
        'bumpNo' : `${i}`
    })

    const post_options = {
        host: 'localhost',
        port: '4567',
        path: '/bump',
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'Content-Length': Buffer.byteLength(postData)}
    }
    const req = http.request(post_options)
    req.write(postData)
    req.end()
}

const delayedSingle = (i) => rxjs
    .of(i)
    .pipe(
        delay((i % 10) * 100))

const bumps = rxjs
    .generate(0, i => i < 1000, i => i + 1)
    .pipe(
        concatMap(delayedSingle))

bumps.subscribe(sendBump)