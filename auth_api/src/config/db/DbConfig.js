import Sequelize from 'sequelize'

const sequelize = new Sequelize(
    "auth-db", "postgres", "lu072324",{
        host: "localhost",
        dialect: "postgres",
        quoteIdentifiers: false,
        define:{
            syncOnAssociation: true,
            timestamp: false,
            underscore: true,
            underscoreAll: true,
            freezeTableName: true
        },
    },
);

sequelize
.authenticate()
.then(() => {
    console.log("Connection has been stablished! ")
})
.catch(err => {
    console.error("Unable connect to the database")
    console.error(err.message)
});
export default sequelize;