/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


document.getElementById("getPerson").addEventListener("click", getPerson);
document.getElementById("getAll").addEventListener("click", getPeople);

function getPerson(){
var id = document.getElementById("id").value;
var url = "http://localhost:8080/jpareststarter/api/p/" + id;
fetch(url)
        .then(res => res.json()) 
        .then(data => {
            console.log("data", data);
            document.getElementById('myDiv').innerHTML = data.name;
        })};
    
function getPeople(){
var url = "http://localhost:8080/jpareststarter/api/p/all"
fetch(url)
        .then(res => res.json()) 
        .then(data => {
            console.log("data", data);
            document.getElementById('myDiva').innerHTML = peopleToTable(data);
        })};
    
    function peopleToTable(arr) {
        var html = arr.map(item => "<tr>"
                + "<td>" + item.id + "</td>"
                + "<td>" + item.name + "</td>"
                + "<td>" + item.talent + "</td>"
                + "</tr>");
        var joinThem = html.join("");
        
        var result = "<table=\"table table-striped\"><tr>"
            + "<th width = 10%>ID</th>"
            + "<th width = 10%>Name</th>"
            + "<th width = 10%>Talent</th>"
            + joinThem + "</table>";
    
    return result;
}