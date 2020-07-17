const URLS = {
    items: '/doctor/api/schedule',
};

const toString = ({id, dateReview, doctor, animal, isFinished}) => {
    let columns = `
<div class="col-md-2">
   ${dateReview}
</div>
<div class="col-md-2">
    ${doctor}
</div>
<div class="col-md-2">
    ${animal}
</div>
<div class="col-md-2">
    <a class="btn btn-info add-treatment" href="/doctor/add-treatment/${id}">Add Treatment</a>
</div>
<div class="col-md-2">
    <form method="post" action="/doctor/api/delete-finished-schedule/${id}">
        <input type="submit" value="Complete" class="btn btn-danger" />
    </form>
</div>
`
    return `<div class="row">${columns}</div>`
};

fetch(URLS.items)
    .then(response => response.json())
    .then(items => {
        let result = '';
        items.forEach(item => {
            if (item.finished === false) {
                const itemString = toString(item);
                result += itemString;
            }
        });

        $('#camp').append(result);
    });
