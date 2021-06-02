const config = require('../configs/database');
const mysql2 = require('mysql2');
const pool = mysql2.createPool(config);

pool.on('error', (err) => {
    console.error(err);
});

module.exports = {
    // Ambil data parking_area
    getParkingArea(req, res) {
        pool.getConnection(function (err, connection) {
            if (err) throw err;
            connection.query(
                `
                SELECT * FROM parking_area;
                `
                , function (error, results) {
                    if (error) throw error;
                    res.send({
                        success: true,
                        message: 'Berhasil ambil data!',
                        data: results
                    });
                });
            connection.release();
        })
    },
    // Ambil data hoops_entity
    getHoopsEntity(req, res) {
        pool.getConnection(function (err, connection) {
            if (err) throw err;
            connection.query(
                `
                SELECT * FROM hoops_entity;
                `
                , function (error, results) {
                    if (error) throw error;
                    res.send({
                        success: true,
                        message: 'Berhasil ambil data!',
                        data: results
                    });
                });
            connection.release();
        })
    },
    // Ambil semua data
    getAll(req, res) {
        pool.getConnection(function (err, connection) {
            if (err) throw err;
            connection.query(`
                select
                    h.place_id,
                    h.place_name,
                    h.place_img,
                    h.place_address,
                    p.park_id,
                    p.park_name,
                    p.park_img,
                    p.park_address,
                    p.park_status,
                    p.available_park,
                    p.parking_location,
                    p.parking_space
                from hoops_entity h
                inner join parking_area p on p.place_id = h.place_id
                `
                , function (error, results) {
                    if (error) throw error;
                    let data = [];
                    let currnetPlaceId = 0;
                    let currentIndex = -1;
                    results.map((value, index) => {
                        if (currnetPlaceId != value.place_id) {
                            currentIndex++
                            data[currentIndex] = {
                                'place_id': value.place_id,
                                'place_name': value.place_name,
                                'place_img': value.place_img,
                                'place_address': value.place_address,
                            }
                            data[currentIndex]['parking'] = []
                            currnetPlaceId = value.place_id
                        }
                        console.log(data[currentIndex])
                        data[currentIndex]['parking'].push({
                            'park_id': value.park_id,
                            'park_name': value.park_name,
                            'park_img': value.park_img,
                            'park_address': value.park_address,
                            'park_status': value.park_status,
                            'available_park': value.available_park, /* ambil ML */
                            'parking_space': value.parking_space, /* json object */
                            'parking_layout': value.parking_layout /* img = url */
                        })
                    })
                    res.send({
                        success: true,
                        message: 'Berhasil ambil data!',
                        hoops_entity: data
                    });
                });
            connection.release();
        })
    },
    // Ambil data parking_area berdasarkan ID
    getParkingAreaID(req, res) {
        let id = req.params.id;
        pool.getConnection(function (err, connection) {
            if (err) throw err;
            connection.query(
                `
                SELECT * FROM parking_area WHERE park_id = ?;
                `
                , [id],
                function (error, results) {
                    if (error) throw error;
                    res.send({
                        success: true,
                        message: 'Berhasil ambil data!',
                        data: results
                    });
                });
            connection.release();
        })
    },
    // Ambil data hoops_entity berdasarkan ID
    getHoopsEntityID(req, res) {
        let id = req.params.id;
        pool.getConnection(function (err, connection) {
            if (err) throw err;
            connection.query(
                `
                SELECT * FROM hoops_entity WHERE place_id = ?;
                `
                , [id],
                function (error, results) {
                    if (error) throw error;
                    res.send({
                        success: true,
                        message: 'Berhasil ambil data!',
                        data: results
                    });
                });
            connection.release();
        })
    },
    // Simpan data parking_area
    addParkingArea(req, res) {
        let data = {
            park_name: req.body.name,
            park_img: req.body.img,
            park_address: req.body.address,
            park_status: req.body.status,
            available_park: req.body.available,
            parking_location: req.body.location,
            parking_space: req.body.space
        }
        pool.getConnection(function (err, connection) {
            if (err) throw err;
            connection.query(
                `
                INSERT INTO parking_area SET ?;
                `
                , [data],
                function (error, results) {
                    if (error) throw error;
                    res.send({
                        success: true,
                        message: 'Berhasil tambah data!',
                    });
                });
            connection.release();
        })
    },
    // Simpan data hoops_entity
    addHoopsEntity(req, res) {
        let data = {
            place_name: req.body.name,
            place_img: req.body.img,
            place_address: req.body.address,
            park_id: req.body.id
        }
        pool.getConnection(function (err, connection) {
            if (err) throw err;
            connection.query(
                `
                INSERT INTO hoops_entity SET ?;
                `
                , [data],
                function (error, results) {
                    if (error) throw error;
                    res.send({
                        success: true,
                        message: 'Berhasil tambah data!',
                    });
                });
            connection.release();
        })
    },
    // Update data parking_area
    editParkingArea(req, res) {
        let dataEdit = {
            park_name: req.body.name,
            park_img: req.body.img,
            park_address: req.body.address,
            park_status: req.body.status,
            available_park: req.body.available,
            parking_location: req.body.location,
            parking_space: req.body.space
        }
        let id = req.params.id;
        pool.getConnection(function (err, connection) {
            if (err) throw err;
            connection.query(
                `
                UPDATE parking_area SET ? WHERE park_id = ?;
                `
                , [dataEdit, id],
                function (error, results) {
                    if (error) throw error;
                    res.send({
                        success: true,
                        message: 'Berhasil edit data!',
                    });
                });
            connection.release();
        })
    },
    // Update data hoops_entity
    editHoopsEntity(req, res) {
        let dataEdit = {
            place_name: req.body.name,
            place_img: req.body.img,
            place_address: req.body.address,
            park_id: req.body.id
        }
        let id = req.params.id;
        pool.getConnection(function (err, connection) {
            if (err) throw err;
            connection.query(
                `
                UPDATE hoops_entity SET ? WHERE place_id = ?;
                `
                , [dataEdit, id],
                function (error, results) {
                    if (error) throw error;
                    res.send({
                        success: true,
                        message: 'Berhasil edit data!',
                    });
                });
            connection.release();
        })
    },
    // Delete data parking_area
    deleteParkingAreaID(req, res) {
        let id = req.params.id;
        pool.getConnection(function (err, connection) {
            if (err) throw err;
            connection.query(
                `
                DELETE FROM parking_area WHERE park_id = ?;
                `
                , [id],
                function (error, results) {
                    if (error) throw error;
                    res.send({
                        success: true,
                        message: 'Berhasil hapus data!'
                    });
                });
            connection.release();
        })
    },
    // Delete data hoops_entity
    deleteHoopsEntityID(req, res) {
        let id = req.params.id;
        pool.getConnection(function (err, connection) {
            if (err) throw err;
            connection.query(
                `
                DELETE FROM hoops_entity WHERE place_id = ?;
                `
                , [id],
                function (error, results) {
                    if (error) throw error;
                    res.send({
                        success: true,
                        message: 'Berhasil hapus data!'
                    });
                });
            connection.release();
        })
    },/*
    // Delete all data
    deleteAll(req,res){
        pool.getConnection(function(err, connection) {
            if (err) throw err;
            connection.query(
                `
                DELETE  FROM 'hoops_entity', 'parking_area', 'hoops_entity';
                `
            ,
            function (error, results) {
                if(error) throw error;  
                res.send({ 
                    success: true, 
                    message: 'Berhasil hapus data!'
                });
            });
            connection.release();
        })
    }*/
}