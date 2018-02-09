var link = document.createElement("a");
link.href = "https://github.com/AdelLarbi/cinox-new.git";
link.alt = "Cinox project github repository";

var img = document.createElement("img");
img.src = "https://cinox-dar.herokuapp.com/new-user.png";
//img.height="128";
//img.width="128";
img.id="Ad-new-user";
img.alt="Ad-new-user";
link.appendChild(img);

document.body.appendChild(link);