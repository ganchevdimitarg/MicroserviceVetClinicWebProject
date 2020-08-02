const URLS = {
    items: '/doctor/api/medicines',
};

const toString = ({name, description}) => {
    let columns = `<div class="col-md-6">
                        <img src="https://images-i.jpimedia.uk/imagefetch/https://inews.co.uk/wp-content/uploads/2019/08/GettyImages-1026417518.jpg?crop=61:45,smart&width=280"
                             alt="Arnica">
                    </div>
                    <div class="col-md-6">
                        <h4>Name: ${name}</h4>
                        <h4>Description: ${description}</h4>
                    </div>
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
