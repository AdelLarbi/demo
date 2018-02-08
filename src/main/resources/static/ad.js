/*<img src="pexels-photo.jpg" alt="Ad" width="128" height="128"/>

var DOM_img = document.createElement("img");
DOM_img.src = "pexels-photo.jpg";
document.body.appendChild(DOM_img);*/

var link = document.createElement("a");
//link.appendChild(document.createTextNode("Anchor"));
link.href = "http://stackoverflow.com/questions/7932759/dom-appendchild-to-insert-images/7932803";
link.alt = "Flash and JS are not enemies!";


var img = document.createElement("img");
img.src = "http://localhost:3000/pexels-photo.jpg";
img.height="128";
img.width="128";
img.alt="Ad";
link.appendChild(img);

document.body.appendChild(link);

