import RowData from "../entity/RowData.js";

/**
 * A class for resolving data on <pre> success </pre> page.
 */
export default class SuccessPageDataResolver {

    resolveDataFromContentEditableRowCorrespondingToSpecificButton(specificButton) {
        let day;
        let time;
        let note;
        let accessible;

        $(specificButton).closest('tr').find('td')
            .each((column, cell) => {

                    if ($(cell).attr('name') === 'day') {
                        /* <pre> ... .find(':selected') ... </pre> finds selected option from
                         * <pre> <select...></select> </pre> element. */
                        day = $(cell).find(':selected').text();
                    }

                    if ($(cell).attr('name') === 'time') {
                        /* <strong>NOTE:</strong> in this cell I placed <pre> Cleave </pre> formatted input,
                         * so I need  to get <strong>it's</strong> value, therefore,
                         * I write:
                         * <pre> ...$(cell).find('input:first').val(); </pre> */
                        time = $(cell).find('input:first').val();
                    }

                    if ($(cell).attr('name') === 'note') {
                        note = $(cell).text();
                    }

                    if ($(cell).attr('name') === 'accessible') {
                        /* <pre> ... .find('input:first') ... </pre> finds selected option from
                         * <pre> <select...></select> </pre> element.
                         * <strong>NOTE:</strong> convert it to lower case to avoid
                         * cast(too <pre> boolean </pre>) exception on server. */
                        accessible = $(cell).find(':selected').text().toLowerCase();
                    }
                }
            );

        return new RowData(day, time, note, accessible);
    }

    resolveDataFromNonContentEditableRowCorrespondingToSpecificButton(specificButton) {
        let day;
        let time;
        let note;
        let accessible;

        $(specificButton).closest('tr').find('td')
            .each((column, cell) => {

                    if ($(cell).attr('name') === 'day') {
                        day = $(cell).text();
                    }

                    if ($(cell).attr('name') === 'time') {
                        time = $(cell).text();
                    }

                    if ($(cell).attr('name') === 'note') {
                        note = $(cell).text();
                    }

                    if ($(cell).attr('name') === 'accessible') {
                        /* <strong>NOTE:</strong> convert it to lower case to avoid
                         * cast(too <pre> boolean </pre>) exception on server. */
                        accessible = $(cell).text().toLowerCase();
                    }
                }
            );

        return new RowData(day, time, note, accessible);
    }

    resolveUsernameForSearchingAgendas() {
        return $('#search-someones-agenda-input').val()
            .toLowerCase();
    }

    resolveUsernameForBanningOrUnbanning() {
        return $('#admin-input').val()
            .toLowerCase();
    }
}