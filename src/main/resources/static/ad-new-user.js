var container = document.createElement("div");
container.id="advertisement";
container.style="text-align:center; margin-bottom:10px";

var link = document.createElement("a");
link.href = "https://github.com/AdelLarbi/cinox-new.git";
link.alt = "Cinox project github repository";

var img = document.createElement("img");
img.src = "https://cinox-dar.herokuapp.com/new-user.png";
img.alt="Ad-new-user";

container.appendChild(link);
link.appendChild(img);
document.body.appendChild(container);