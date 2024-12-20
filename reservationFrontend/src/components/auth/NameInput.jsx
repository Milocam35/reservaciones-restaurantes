const NameInput = ({value, onChange}) => (
    <div>
      <label htmlFor="user_name" className="block text-sm font-medium leading-6 text-gray-900">
        User name
      </label>
      <div className="mt-2">
        <input
          id="name"
          name="name"
          type="text"
          value={value}
          onChange={onChange}
          autoComplete="name"
          required
          className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
        />
      </div>
    </div>
  );
  
  export default NameInput;