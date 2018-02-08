var link = document.createElement("a");
link.href = "http://stackoverflow.com/questions/7932759/dom-appendchild-to-insert-images/7932803";
link.alt = "Flash and JS are not enemies!";

var img = document.createElement("img");
img.src = "http://localhost:3000/tracked-user.jpg";
img.height="150";
img.width="300";
img.alt="Ad-tracked-user";
link.appendChild(img);

document.body.appendChild(link);