async function getItemsAs2() {
    try {
        let response = await fetch("http://localhost:8080/todo/index");
        let data = await response.json();

        const table = document.querySelector('.display');
        const thead = table.querySelector('thead')

        for (let i = 0; i < data.length; i++) {

            let tr = document.createElement('tr');
            tr.innerHTML = '<td>${data[i].id}</td>' +
            '<td>${data[i].description}</td>' +
            '<td>${data[i].created}</td>'+
            '<td>${data[i].done}</td>';
            thead.appendChild(tr);
}

    } catch (err) {
        console.log(err);
}
}

async function getItemsAs () {
    try {
        let response = await fetch("http://localhost:8080/job4j_todo/index")
        let data = await response.json();
for (let i = 0; i < data.length; i++) {
            let id = data[i]['id'];
            let desc = data[i]['description'];
            let done = data[i]['done'];
            let created = data[i]['created']
            console.log(id);
            console.log(desc);
            console.log(done);
            console.log(created);
}
    } catch (err) {
        console.log(err);
}
}