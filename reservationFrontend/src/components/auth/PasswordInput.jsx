const PasswordInput = ({value, onChange}) => (
  <div>
    <label htmlFor="password" className="block text-sm font-medium leading-6 text-gray-900">
      Password
    </label>
    <div className="mt-2">
      <input
        id="password"
        name="password"
        type="password"
        value={value}
        onChange={onChange}
        autoComplete="current-password"
        required
        className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
      />
    </div>
  </div>
);

export default PasswordInput;
