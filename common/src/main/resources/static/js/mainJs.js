$(function () {
    $('div[id="imagePrev"]').click(function () {

        let tags = [].map.call(this.getElementsByClassName('tags'), function (obj) {
             return obj.innerText;
         });

        let itemName = [].map.call(this.getElementsByClassName('itemName'), function (obj) {
            return obj.innerText;
        });

        let itemValue = this.getElementsByClassName('attributeValue')

        let ItemAttributeMerg = this.getElementsByClassName('ItemAttributeMerg');
        let attributeValue = [];
        let attributeName = [];


        for (let i = 0; i < ItemAttributeMerg.length; i++) {
            attributeValue[i] = ItemAttributeMerg[i].children[0].innerText;
            attributeName[i] = ItemAttributeMerg[i].children[1].innerText;
        }

        let er


        let paru

        for (let i =0; i < itemName.length; i++){

        paru = document.createElement("div");
        paru.className = "form-group";
        paru.innerHTML = "" +
            "<label for='recipient-name' class='col-form-label'>" + "Item name: " + itemName[i] + "</label>" +
            "<br>" +
            "<label for='recipient-name' class='col-form-label'>" + "Tags: " + tags[i]  + "</label>" +
            "<br>"

            for (let j =0; j < attributeValue.length ; j++) {
                if (attributeValue[j] != "1" && attributeName[j] != "1")
                paru.innerHTML +=
                    "<label for='recipient-name' class='col-form-label'>" + attributeName[j] + ": " + attributeValue[j] + "</label>" + "<br>"

            }
        er = document.getElementById("itemModalContext");
            er.appendChild(paru);
}

    });
});

function adminPage(){

    fetch("/admin",{
        method: "GET",
        headers:{"Content-Type": "application/json"},
    }).then( response => {
        if (response.status === 405 || response.status === 500){
            toastr.options.positionClass = "toast-bottom-right";
            toastr.error("Error");
            return false;
        } else if (response.status === 200) {
            toastr.options.positionClass = "toast-bottom-right";
            toastr.success("Item added");
            update();
        }
    });

    setTimeout(function () {
        location.reload();
    }, 5000);

    t();
}

function t(){
    window.location.href="http://localhost:8081/admin";
}

$("#tagModel").on("hidden.bs.modal", function(){
    $("#itemModalContext").empty();
});