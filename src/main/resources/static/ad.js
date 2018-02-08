/*<img src="pexels-photo.jpg" alt="Ad" width="128" height="128"/>

var DOM_img = document.createElement("img");
DOM_img.src = "pexels-photo.jpg";
document.body.appendChild(DOM_img);*/

var link = document.createElement("a");
//link.appendChild(document.createTextNode("Anchor"));
link.href = "http://stackoverflow.com/questions/7932759/dom-appendchild-to-insert-images/7932803";
link.alt = "Flash and JS are not enemies!";


var req = new XMLHttpRequest();
req.open('GET', document.location, false);
req.send(null);
var headers = req.getAllResponseHeaders().toLowerCase();
//Console.log(headers);
alert(headers);

var img = document.createElement("img");
img.src = "http://localhost:3000/tracked-user.jpg";
img.height="128";
img.width="128";
img.alt="Ad-tracked";
link.appendChild(img);

document.body.appendChild(link);

/*var log = document.createElement("p");
log.innerHTML = "hello !";
link.appendChild(log);*/

