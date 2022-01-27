const theme = document.querySelector("#theme-link");

const currentTheme = localStorage.getItem("theme");

if (currentTheme == "dark") {
  theme.href="../static/css/dark-theme.css"
  document.getElementById('btnTheme').innerHTML = "Light";
}


function applyTheme() {


    if (theme.getAttribute("href") == "../static/css/light-theme.css") {
        document.getElementById('btnTheme').innerHTML = "Light";
        theme.href = "../static/css/dark-theme.css";
        localStorage.setItem("theme", "dark");
    } else {
        document.getElementById('btnTheme').innerHTML = "Dark";
        theme.href = "../static/css/light-theme.css";
        localStorage.setItem("theme", "light");
    }

    
    
}

