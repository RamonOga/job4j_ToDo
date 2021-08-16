function getItemsAj () {
    $.ajax({
        url : "http://localhost:8080/job4j_todo/index",
        async: true,
        method : "GET",
        dataType : "json",
        success : function (data) {

            const table = document.querySelector('.table');
            const thead = table.querySelector('thead')

            for (let i = 0; i < data.length; i++) {
                let img;
                let item = data[i];
                let id = item['id'];
                let created = data[i]['created'];
                let desc = item['description'];
                let done = item['done'];
                if (done) {
                    img = '<img src="img/true.png" width="15" height="15">';
                } else {
                    img = '<img src="img/false.png" width="15" height="15">';
                }
                let tr = document.createElement('tr');
                tr.innerHTML = '<td>' + id + '</td>' +
                    '<td>' + desc +  '</td>'+
                    '<td>' + created +  '</td>' +
                    '<td>' + img +  '</td>';
                thead.appendChild(tr);
            }
        }
    });
}

function sendData() {
    let description = $('#description').val();
    $.ajax ({
        url: "http://localhost:8080/job4j_todo/index",
        dataType: "json",
        method: "POST",
        data: {description : description},
    }).done(function () {

    }).fail(function (err) {

    });
};