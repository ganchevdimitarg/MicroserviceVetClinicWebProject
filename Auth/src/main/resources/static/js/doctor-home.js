const URLS = {
    items: '/doctor/api/doctor-home',
};

const toString = ({id, name, specialization, description}) => {
    let columns = ` <div class="col-md-6">
                <h4>Name: ${name}</h4>
                <h4>Specialization: ${specialization}</h4>
                <h4>Description: ${description}</h4>
                <div class="container">
                    <div class="row"></div>
                    <a class="col-md-3 btn btn-info" href="/doctor/schedule">Schedule</a>
                    <a class="col-md-3 btn btn-info" href="/doctor/medicine">Medicine</a>
                </div>
            </div>
`
    return `<div class="row">${columns}</div>`
};

fetch(URLS.items)
    .then(response => response.json())
    .then(item => {
        let result = '';
        const itemString = toString(item);
        result += itemString;

        $('#loader-page').hide()
        $('#campd').append(result);
    });
$('#loader-page').hide()
