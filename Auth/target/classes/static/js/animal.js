const URLS = {
    items: '/user/api/pet',
};

const toString = ({id, breed, name, age, disease, medicine}) => {
    let columns = ` <div class="col-md-2">
                    ${breed}
                </div>
                <div class="col-md-2">
                    ${name}
                </div>
                <div class="col-md-2">
                    ${age}
                </div>
                <div class="col-md-2">
                    <div>${disease}</div>
                </div>
                <div class="col-md-2">
                    <div>${medicine}</div>
                </div>
                <form action="/user/api/delete-user-animal/${id}" method="post">
                    <input type="submit" value="Delete" class="col-md-3 btn btn-danger"/>
                </form>
`
    return `<div class="row">${columns}</div>`
};

fetch(URLS.items)
    .then(response => response.json())
    .then(items => {
        let result = '';
        items.forEach(item => {
                const itemString = toString(item);
                result += itemString;
        });

        $('#loader-page').hide()
        $('#camp').append(result);
    });
$('#loader-page').hide()