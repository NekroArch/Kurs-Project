
const theme = document.querySelector("#theme-link");

const currentTheme = localStorage.getItem("theme");

if (currentTheme == "dark") {
  theme.href="/css/dark-theme.css"
  document.getElementById('btnTheme').innerHTML = "Light";
}else{
    theme.href="/css/light-theme.css"
    document.getElementById('btnTheme').innerHTML = "Dark";
}


function applyTheme() {

    if (theme.getAttribute("href") == "/css/light-theme.css") {
        document.getElementById('btnTheme').innerHTML = "Light";
        theme.href = "/css/dark-theme.css";
        localStorage.setItem("theme", "dark");
    } else {
        document.getElementById('btnTheme').innerHTML = "Dark";
        theme.href = "/css/light-theme.css";
        localStorage.setItem("theme", "light");
    }

}