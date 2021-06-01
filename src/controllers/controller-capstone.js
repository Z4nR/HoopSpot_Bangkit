const config = require('../configs/database');
const mysql2 = require('mysql2');
const pool = mysql2.createPool(config);

pool.on('error',(err)=> {
    console.error(err);
});

module.exports ={
    // Ambil data parking_area
    getParkingArea(req,res){
        pool.getConnection(function(err, connection) {
            if (err) throw err;
            connection.query(
                `
                SELECT * FROM parking_area;
                `
            , function (error, results) {
                if(error) throw error;  
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
    getHoopsEntity(req,res){
        pool.getConnection(function(err, connection) {
            if (err) throw err;
            connection.query(
                `
                SELECT * FROM hoops_entity;
                `
            , function (error, results) {
                if(error) throw error;  
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
    getAll(req,res){
        pool.getConnection(function(err, connection) {
            if (err) throw err;
            connection.query(
                /* Cloud Deploy Code */
                `
                select
                    h.place_id,
                    h.place_name,
                    h.place_img,
                    h.place_address,
                    json_array_agg(
                        json_object(
                            'park_id', p.park_id,
                            'park_name', p.park_name,
                            'park_img', p.park_img,
                            'park_address', p.park_address,
                            'park_status', p.park_status,
                            'available_park', p.available_park,
                            'parking_location', p.parking_location,
                            'parking_space', p.parking_space
                        )
                    ) as parking_area
                from hoops_entity h
                inner join parking_area p on p.place_id = h.place_id
                group by h.place_id, h.place_name;
                `
                /*select
                    h.place_id,
                    h.place_name,
                    h.place_img,
                    h.place_address,
                    concat(
                        '[',
                            group_concat(
                                json_object(
                                    'park_id', p.park_id,
                                    'park_name', p.park_name,
                                    'park_img', p.park_img,
                                    'park_address', p.park_address,
                                    'park_status', p.park_status,
                                    'available_park', p.available_park,
                                    'parking_location', p.parking_location,
                                    'parking_space', p.parking_space
                                )
                            ),
                        ']'
                    ) as parking_area
                    from hoops_entity h
                    inner join parking_area p on p.place_id = h.place_id
                    group by h.place_id, h.place_name;*/
            , function (error, results) {
                if(error) throw error;
                /*let ob4 = JSON.parse(results[0].parking_area);
                delete results[0].parking_area;
                //results.place_address.push = ob4;
                results.push(ob4);
                //console.log(ob4);
                //console.log(results.splice(results[0].parking_area));*/
                res.send({ 
                    success: true, 
                    message: 'Berhasil ambil data!',
                    hoops_entity: results
                });
            });
            connection.release();
        })
    },
    // Ambil data parking_area berdasarkan ID
    getParkingAreaID(req,res){
        let id = req.params.id;
        pool.getConnection(function(err, connection) {
            if (err) throw err;
            connection.query(
                `
                SELECT * FROM parking_area WHERE park_id = ?;
                `
            , [id],
            function (error, results) {
                if(error) throw error;  
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
    getHoopsEntityID(req,res){
        let id = req.params.id;
        pool.getConnection(function(err, connection) {
            if (err) throw err;
            connection.query(
                `
                SELECT * FROM hoops_entity WHERE place_id = ?;
                `
            , [id],
            function (error, results) {
                if(error) throw error;  
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
    addParkingArea(req,res){
        let data = {
            park_name : req.body.name,
            park_img : req.body.img,
            park_address : req.body.address,
            park_status : req.body.status,
            available_park : req.body.available,
            parking_location : req.body.location,
            parking_space : req.body.space
        }
        pool.getConnection(function(err, connection) {
            if (err) throw err;
            connection.query(
                `
                INSERT INTO parking_area SET ?;
                `
            , [data],
            function (error, results) {
                if(error) throw error;  
                res.send({ 
                    success: true, 
                    message: 'Berhasil tambah data!',
                });
            });
            connection.release();
        })
    },
    // Simpan data hoops_entity
    addHoopsEntity(req,res){
        let data = {
            place_name : req.body.name,
            place_img : req.body.img,
            place_address : req.body.address,
            park_id : req.body.id
        }
        pool.getConnection(function(err, connection) {
            if (err) throw err;
            connection.query(
                `
                INSERT INTO hoops_entity SET ?;
                `
            , [data],
            function (error, results) {
                if(error) throw error;  
                res.send({ 
                    success: true, 
                    message: 'Berhasil tambah data!',
                });
            });
            connection.release();
        })
    },
    // Update data parking_area
    editParkingArea(req,res){
        let dataEdit = {
            park_name : req.body.name,
            park_img : req.body.img,
            park_address : req.body.address,
            park_status : req.body.status,
            available_park : req.body.available,
            parking_location : req.body.location,
            parking_space : req.body.space
        }
        let id = req.params.id;
        pool.getConnection(function(err, connection) {
            if (err) throw err;
            connection.query(
                `
                UPDATE parking_area SET ? WHERE park_id = ?;
                `
            , [dataEdit, id],
            function (error, results) {
                if(error) throw error;  
                res.send({ 
                    success: true, 
                    message: 'Berhasil edit data!',
                });
            });
            connection.release();
        })
    },
    // Update data hoops_entity
    editHoopsEntity(req,res){
        let dataEdit = {
            place_name : req.body.name,
            place_img : req.body.img,
            place_address : req.body.address,
            park_id : req.body.id
        }
        let id = req.params.id;
        pool.getConnection(function(err, connection) {
            if (err) throw err;
            connection.query(
                `
                UPDATE hoops_entity SET ? WHERE place_id = ?;
                `
            , [dataEdit, id],
            function (error, results) {
                if(error) throw error;  
                res.send({ 
                    success: true, 
                    message: 'Berhasil edit data!',
                });
            });
            connection.release();
        })
    },
    // Delete data parking_area
    deleteParkingAreaID(req,res){
        let id = req.params.id;
        pool.getConnection(function(err, connection) {
            if (err) throw err;
            connection.query(
                `
                DELETE FROM parking_area WHERE park_id = ?;
                `
            , [id],
            function (error, results) {
                if(error) throw error;  
                res.send({ 
                    success: true, 
                    message: 'Berhasil hapus data!'
                });
            });
            connection.release();
        })
    },
    // Delete data hoops_entity
    deleteHoopsEntityID(req,res){
        let id = req.params.id;
        pool.getConnection(function(err, connection) {
            if (err) throw err;
            connection.query(
                `
                DELETE FROM hoops_entity WHERE place_id = ?;
                `
            , [id],
            function (error, results) {
                if(error) throw error;  
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