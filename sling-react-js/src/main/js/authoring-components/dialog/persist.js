export default (data) => {
    console.log(data)
    return new Promise( (resolve, reject) => {
        setTimeout(() => reject("TODO: implement"), 2000)
    })
}