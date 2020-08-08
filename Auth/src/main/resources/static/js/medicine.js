const URLS = {
    items: '/doctor/api/medicines',
};

const toString = ({name, description, imageUrl}) => {
    let columns = `<div class="col-md-6">
                        <img src="${imageUrl}" class="img-pic" alt="medicine-image" >
                    </div>
                    <div class="col-md-6">
                        <h4>Name: ${name}</h4>
                        <h4>Description: ${description}</h4>
                    </div>`

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
