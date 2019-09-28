

let array = ["Christina", "Malene", "Tea", "Frederikke", "Sarah", "Jacqueline", "Mie", "Niels"];
console.log(array)

// 1. Using the fitler() method
 let a = array.filter(function(letter){
    if(letter.match('a')){
        return letter;
    }
})

console.log(a);

// 1. Using the map() method 
let reversed = array.map(item => item).reverse();

console.log(reversed);

// 2.a Implement a function myFilter()
Array.prototype.mfilter =  function (fun) {
    var filtered = []; 
    for(let i = 0; i < this.length; i++) {
      if (fun(this[i], i, this)) filtered.push(this[i]);
    }
    return filtered;
  };

  let b = array.mfilter(function(letter) {
      if(letter.match('a')) {
          return letter; 
      }
    }); 

    console.log(b); 

//4. Getting really comfortable with filter and map
// A.  Use map + a sufficient callback to map numbers into this array:

//Given array from assignment
var numbers = [1, 3, 5, 10, 11];

let arr = numbers.map(function(num, index){
  return num + numbers[index +1]
});

console.log(arr)

// B. Use map() to create the <a>’s for a navigation set and eventually a string like below (use join() to get the string of <a>’s):
var arr1= array.map(name => "<a href =''>" + name + "</a>");
arr1.unshift("<nav>")
arr1.push("</nav>")
console.log(arr1.join("\n"));


// C. Use map()+(join + ..) to create a string, representing a two column table, for the data given below:
var names = [{name:"Lars",phone:"1234567"}, {name: "Peter",phone: "675843"}, {name: "Jan", phone: "98547"},{name: "Bo", phone: "79345"}];

var arr2 = names.map(function(column){
  let table = "<table>";
  let endTable ="</table>";
  let tr = "<tr>"
  let endTr = "</tr>"
  return table + tr + "<td>" + column.name+ "</td>"
      + "<td>" + column.phone + "</td>" + endTr + endTable;
});
  
console.log(arr2.join("\n"));


// D. Create a single html-file and test the two examples given above.
//Go to HTML-page named "Period2Day1.html"
document.getElementById("arrayList").innerHTML = arr1.join("\n");
document.getElementById("nameTable").innerHTML = arr2.join("\n");

// E. Add a button with a click-handler and use the filter method to find only names containing the letter
//Didnt figure this one out 
document.getElementById("submit").onclick = function(){

  function findA(name){
  if(name.name.includes('a'))
    return name;
}
let find = arr1.filter(findA);

document.getElementById("nameTable").innerHTML = find(arr2);
}

//5. Reduce
// A. Use join to create a single string from all, with names: comma-, space. and  # - separated.
//given array from assignment
var all= ["Lars", "Peter", "Jan", "Bo"];
console.log(all.join(", "))
console.log(all.join(" "))
console.log(all.join("#"))

// B. Create a reducer callback that, with reduce(..),  will return the sum (105) of all values in numbers
// Given this array: 
var numbers = [2, 3, 67, 33]; 

const reduce = (accumulator, currentvalue) => accumulator + currentvalue;
console.log(numbers.reduce(reduce));