function searchJs(){
    let text = document.getElementById("searchBtn").value;

    fetch("/collectionSearch?collectionName=" + text,{
        method: "GET",
        headers:{"Content-Type": "application/json"},
    }).then( response => {
        if (response.status === 405 || response.status === 500){
            toastr.options.positionClass = "toast-bottom-right";
            toastr.error("Error");
        } else if (response.status === 200) {
            window.location = "/collectionSearch?collectionName=" + text;
        }
    });
}