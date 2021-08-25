function getAllItems (user_id, checkbox) {
   // console.log("start getAllItems");
    $.ajax({
        url : "http://localhost:8080/job4j_todo/index",
        async: true,
        method : "GET",
        dataType : "json",
        data: {
            user_id : user_id
        },
        success : function (data) {
            $("table td").remove(); // чистим таблицу
            const table = document.querySelector('.table');
            const thead = table.querySelector('thead')
            for (let i = 0; i < data.length; i++) {
                let img;
                let item = data[i];
                let id = item['id'];
                let created = data[i]['created'];
                let desc = item['description'];
                let done = item['done'];
                let button;
                let categories = item['categories'];
                console.log(categories);

               if ( !checkbox && done ) { // если стоит checkbar _не отсеиваем_ выполненые заяки
                    continue;
                }

                if (done) { // грузим соответсвтующую картинку
                    button = ''
                    img = '<img src="img/true.png" width="15" height="15">';
                } else {
                    button = '<button type="submit" class="btn btn-primary" onclick="doneItem(' + id + ')">Done</button>'
                    img = '<img src="img/false.png" width="15" height="15">';
                }
                let tr = document.createElement('tr'); // заполняем таблицу
                tr.innerHTML = '<td>' + id + '</td>' +
                    '<td>' + desc +  '</td>'+
                    '<td>' + created +  '</td>' +
                    '<td>' + img +  '</td>' +
                    '<td>' + button +  '</td>';
                thead.appendChild(tr);
            }
        }
    });
}

function doneItem(user_id) {
    console.log("start DoneItem");
    $.ajax ({
        url: "http://localhost:8080/job4j_todo/done",
        dataType: "json",
        method: "POST",
        data: {id : user_id},
    });
}

function sendData(user_id) { // отправка данных заявки
    console.log("start sendData");
    let description = $('#description').val();
    let itemCats = "[" + $('#itemCats').val() + "]";
    $.ajax ({
        url: "http://localhost:8080/job4j_todo/index",
        dataType: "json",
        method: "POST",
        data: {description : description, user_id : user_id, itemCats: itemCats},
    });
}

function checkCheckbox(user) { // проверка стоит ли checkbar
    const checkbox = document.querySelector('#checkbox').checked;
    getAllItems(user, checkbox);
}

