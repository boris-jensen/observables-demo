const express = require('express')
const app = express()
const port = 3000

let doWait = false

// send "a", get 3 'completions': ["a1", "a2", "a3"], with varied response times
app.get('/typeahead', (req, res) => {
    const response = [1, 2, 3].map(i => `${req.query.prefix}${i}`)
    const timeout = doWait ? 2000 : 0
    doWait = !doWait;

    setTimeout(() => res.send(response), timeout)
})

app.use(express.static('public'))

app.listen(port, () => console.log(`Rx app listening on port ${port}!`))