var link = document.createElement("a");
link.href = "http://stackoverflow.com/questions/7932759/dom-appendchild-to-insert-images/7932803";
link.alt = "Flash and JS are not enemies!";

var img = document.createElement("img");
img.src = "https://cinox-dar.herokuapp.com/new-user.jpg";
img.height="128";
img.width="128";
img.alt="Ad-new-user";
link.appendChild(img);

document.body.appendChild(link);