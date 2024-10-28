const TableForm = ({ restaurant, selectedTable, setSelectedTable }) => {
    const tableList = restaurant.tableList;

    return (
        <div>
            <h1 className="font-semibold">Elige Una Mesa Por Capacidad:</h1>
            <div className="grid grid-cols-12 gap-4 mx-auto mt-2">
                {tableList.map((table) => (
                    <div
                        key={table.tableId}
                        className={`flex items-center justify-center border border-neutral-950 w-10 h-10 rounded-full cursor-pointer 
                            ${selectedTable === table ? 'bg-black text-white' : 'hover:bg-gray-200'}`}
                        onClick={() => setSelectedTable(table)}
                    >
                        {table.capacity}
                    </div>
                ))}
            </div>
        </div>
    );
}

export default TableForm;


