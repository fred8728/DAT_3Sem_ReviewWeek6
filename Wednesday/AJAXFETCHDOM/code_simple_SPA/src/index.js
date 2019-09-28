import 'bootstrap/dist/css/bootstrap.css'
import jokes from "./jokes";




document.getElementById("submit").addEventListener("click", getJoke);

function getJoke(){
var url = "https://studypoints.info/jokes/api/jokes/period/hour"
fetch(url)
        .then(res => res.json()) 
        .then(data => {
            console.log("data", data);
            document.getElementById('myDiv').innerHTML = data.joke;
        })};