const express = require('express')
const app = express()
const port = 3000

let doWait = false

app.get('/ping', (req, res) => res.send('pong'))

app.get('/typeahead', (req, res) => {
    const response = [1, 2, 3].map(i => `${req.query.prefix}${i}`)
    const timeout = doWait ? 2000 : 0
    doWait = !doWait;

    setTimeout(() => res.send(response), timeout)
})

app.use(express.static('public'))

app.listen(port, () => console.log(`Rx app listening on port ${port}!`))