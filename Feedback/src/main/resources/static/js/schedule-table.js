const URLS = {
    items: '/api/schedule',
};

const toString = ({dateReview, doctor, animal}) => {
    let columns = `
 <div class="col-md-2">
 [[${dateReview}]]
 </div>
 <div class="col-md-2">
 [[${doctor}]]
 </div>
 <div class="col-md-2">
 [[${animal}]]
 </div>
`
    // columns += finished
    //     ? '<td></td>'
    //     : `<td>
    //         <form class="buy-item-form" data-id=${id} action="/api/items/add-to-user/${id}" method="post">
    //             <button class="btn btn-info">Buy</button>
    //         </form>
    //        </td>`
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

        $('#items-table').html(result);
    });

// fetch(URLS.schedules)
//     .then(responses => responses.json())
// .then(schedules => {
//     let result = '';
//
// schedules.forEach(schedule => {
//     const scheduleString = toString(schedule);
// result += scheduleString;
// });
//
// $('#schedule-table').html(result);
// });

// $('#schedule-table').on('submit', '.buy-item-form', function (ev) {
//     const url = $(this).attr('action');
//
//     fetch(url, { method: 'post' })
//         .then(data => {
//         console.log(data)
//         loader.hide();
//     window.location = '/items/merchant';
// });
//
//     ev.preventDefault();
//     return false;
// });
