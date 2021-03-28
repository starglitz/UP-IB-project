export const COLUMNS = [
    {
        Header: 'Id',
        accessor: 'id',
        disableFilters: true,
        sticky: 'left'
    },
    {
        Header: 'First Name',
        accessor: 'first_name',
        sticky: 'left'
    },
    {
        Header: 'Last Name',
        accessor: 'last_name',
        sticky: 'left'
    },
    {
        Header: 'City',
        accessor: 'city',
    },
    {
        Header: 'Country',
        accessor: 'country'
    },
    {
        Header: 'Phone',
        accessor: 'phone'
    },
    {
        Header: 'Email',
        accessor: 'email'
    },
    {
        Header: 'Age',
        accessor: 'age'
    },
]

export const GROUPED_COLUMNS = [
    {
        Header: 'Id',
        accessor: 'id'
    },
    {
        Header: 'Name',
        columns: [
            {
                Header: 'First Name',
                accessor: 'first_name'
            },
            {
                Header: 'Last Name',
                accessor: 'last_name'
            }
        ]
    },
    {
        Header: 'Info',
        columns: [
            {
                Header: 'Date of Birth',
                accessor: 'date_of_birth'
            },
            {
                Header: 'Country',
                accessor: 'country'
            },
            {
                Header: 'Phone',
                accessor: 'phone'
            }
        ]
    }
]