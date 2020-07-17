const URLS = {
    items: '/user/api/user-home',
};

const toString = ({id, name, email, address, phoneNumber}) => {
    let columns = `
            <div class="col-md-6">
                <img src="https://adayinthelifeimages.com/wp-content/uploads/2016/05/brisbane-headshots-commercial-photography-09.jpg" alt="">
            </div>
            <div class="col-md-6" id="item">
                <h4>Name: ${name}</h4>
                <h4>Email: ${email}</h4>
                <h4>Address: ${address}</h4>
                <h4>PhoneNumber: ${phoneNumber}</h4>
                <div class="container">
                    <div class="row"></div>
                    <a class="col-md-3 btn btn-info" href="/user/pet">Pets</a>
                    <form action="/user/api/delete-user" method="post">
                        <input type="submit" value="Delete" class="col-md-3 btn btn-danger"/>
                    </form>
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

        $('#camp').append(result);
    });