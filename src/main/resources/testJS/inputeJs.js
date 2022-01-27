
function switchBtnColor(){
    const currentThemee = localStorage.getItem("theme");

    if (currentThemee == "dark") {
        colorBtn(0);
    }else{
        colorBtn(1);
    }
}

function colorBtn(switchNumber){
    if(switchNumber == 0){

    document.querySelectorAll('.form-control').forEach(function(elem){
        elem.style.backgroundColor = "#1C1C1C";

    });

    }else{

    document.querySelectorAll('.form-control').forEach(function(elem){
        elem.style.backgroundColor = "#FFFFFF";

    });
    }
        
}
