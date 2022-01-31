$(function () {
  $('button[id="nameBtn"]').click(function () {

    localStorage.setItem("user",this.innerText);
  
    postUser(JSON.stringify(this.innerText));

  })
});

function checkedAll(flag){
  let table = document.getElementById('table');

    if(flag == 0){
    for(let i = 0; i < table.rows.length; i++){
      let row = table.rows[i];
      row.cells[0].getElementsByTagName('input')[0].checked = true;
    }
  }else{
    for(let i = 0; i < table.rows.length; i++){
      let row = table.rows[i];
      row.cells[0].getElementsByTagName('input')[0].checked = false;
    }
  }
};

function changeStatus(){
    let table = document.getElementById("table");
    let nameAndStatus = [];

    for(let i = 0; i < table.rows.length; i++){
         let row = table.rows[i];
           if(row.cells[0].getElementsByTagName('input')[0].checked){
            nameAndStatus.push({
                "name":row.cells[1].getElementsByTagName('button')[0].innerText,
                "status":row.cells[3].getElementsByTagName('div')[0].innerText
            });
          }
       }
    postStatus(JSON.stringify(nameAndStatus));
};

function deleteUser(){
   let table = document.getElementById("table");
   let name = [];
   for(let i = 0; i < table.rows.length; i++){
     let row = table.rows[i];
       if(row.cells[0].getElementsByTagName('input')[0].checked){
        name.push(row.cells[1].getElementsByTagName('button')[0].innerText);
      }
   }

   postDeleteUser(JSON.stringify(name));
};

function changeRole(){
  let table = document.getElementById("table");
  let nameAndRole = [];
  

  for(let i = 0; i < table.rows.length; i++){
    let row = table.rows[i];  
    if(row.cells[0].getElementsByTagName('input')[0].checked && row.cells[4].getElementsByTagName('div')[0].innerText == "ROLE_ADMIN"){
      nameAndRole.push({
        "name":row.cells[1].getElementsByTagName('button')[0].innerText,
        "roleId":2
      });
    }else if (row.cells[0].getElementsByTagName('input')[0].checked && row.cells[4].getElementsByTagName('div')[0].innerText == "ROLE_USER"){
      nameAndRole.push({
        "name":row.cells[1].getElementsByTagName('button')[0].innerText,
        "roleId":1
      });
    }
  }

  postRole(JSON.stringify(nameAndRole))
}

function postUser(data){
  window.location = "/profile/userCollection?name=" + data;
};

function postDeleteUser(data){
   let xhr = new XMLHttpRequest();
   let url = "/admin/delete"

   xhr.open("DELETE", url, true);

   xhr.setRequestHeader("Content-Type", "application/json")

   xhr.send(data);
   update();
};

function postStatus(data){
    let xhr = new XMLHttpRequest();
    let url = "/admin/status"

    xhr.open("PATCH", url, true);

    xhr.setRequestHeader("Content-Type", "application/json")

    xhr.send(data);
    update();
};

function postRole(data){
  let xhr = new XMLHttpRequest();
  let url = "/admin/role"

  xhr.open("PATCH", url, true);

  xhr.setRequestHeader("Content-Type", "application/json")

  xhr.send(data);
  update();
};

function update(){
    setTimeout(function(){
	    location.reload();
    }, 1000);
}
