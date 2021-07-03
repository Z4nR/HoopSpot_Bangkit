/* eslint-disable array-callback-return */
/* eslint-disable no-plusplus */
/* eslint-disable camelcase */
/* eslint-disable no-console */
const mysql2 = require('mysql2');
const config = require('../configs/database');

const pool = mysql2.createPool(config);

pool.on('error', (err) => {
  console.error(err);
});

module.exports = {
  /* Ambil semua data */
  getAll(req, res) {
    pool.getConnection((err, connection) => {
      if (err) throw err;
      connection.query(
        `
                select
                    h.place_id,
                    h.place_name,
                    h.place_img,
                    h.place_address,
                    p.park_id,
                    p.park_name,
                    p.park_img,
                    p.park_address,
                    p.park_layout,
                    p.park_video
                from hoops_entity h
                inner join parking p on p.place_id = h.place_id;
                `,
        (error, results) => {
          if (error) throw error;
          const data = [];
          let currnetPlaceId = 0;
          let currentIndex = -1;
          results.map((value) => {
            if (currnetPlaceId !== value.place_id) {
              currentIndex++;
              data[currentIndex] = {
                place_id: value.place_id,
                place_name: value.place_name,
                place_img: value.place_img,
                place_address: value.place_address,
              };
              data[currentIndex].parking = [];
              currnetPlaceId = value.place_id;
            }
            data[currentIndex].parking.push({
              park_id: value.park_id,
              park_name: value.park_name,
              park_img: value.park_img,
              park_address: value.park_address,
              park_layout: value.park_layout, /* img = url */
              park_video: value.park_video,
            });
          });
          res.send({
            success: true,
            message: 'Berhasil ambil data!',
            hoops_entity: data,
          });
        },
      );
      connection.release();
    });
  },

  /* Ambil data hoops_entity */
  getHoopsEntity(req, res) {
    pool.getConnection((err, connection) => {
      if (err) throw err;
      connection.query(
        `
                SELECT * FROM hoops_entity;
                `,
        (error, results) => {
          if (error) throw error;
          res.send({
            success: true,
            message: 'Berhasil ambil data!',
            hoops_entity: results,
          });
        },
      );
      connection.release();
    });
  },

  /* Ambil data parking */
  getParking(req, res) {
    pool.getConnection((err, connection) => {
      if (err) throw err;
      connection.query(
        `
                SELECT * FROM parking;
                `,
        (error, results) => {
          if (error) throw error;
          res.send({
            success: true,
            message: 'Berhasil ambil data!',
            parking: results,
          });
        },
      );
      connection.release();
    });
  },

  /* Ambil data hoops_entity berdasarkan ID */
  getHoopsEntityID(req, res) {
    const { place_id } = req.params;
    pool.getConnection((err, connection) => {
      if (err) throw err;
      connection.query(
        `
                select
                    h.place_id,
                    h.place_name,
                    h.place_img,
                    h.place_address,
                    p.park_id,
                    p.park_name,
                    p.park_img,
                    p.park_address,
                    p.park_layout,
                    p.park_video
                from hoops_entity h
                inner join parking p on p.place_id = h.place_id
                where h.place_id = ?;
                `,
        [place_id],
        (error, results) => {
          if (error) throw error;
          const data = [];
          let currnetPlaceId = 0;
          let currentIndex = -1;
          results.map((value) => {
            if (currnetPlaceId !== value.place_id) {
              currentIndex++;
              data[currentIndex] = {
                place_id: value.place_id,
                place_name: value.place_name,
                place_img: value.place_img,
                place_address: value.place_address,
              };
              data[currentIndex].parking = [];
              currnetPlaceId = value.place_id;
            }
            data[currentIndex].parking.push({
              park_id: value.park_id,
              park_name: value.park_name,
              park_img: value.park_img,
              park_address: value.park_address,
              park_layout: value.park_layout, /* img = url */
              park_video: value.park_video,
            });
          });
          res.send({
            success: true,
            message: 'Berhasil ambil data!',
            hoops_entity: data,
          });
        },
      );
      connection.release();
    });
  },

  /* Ambil data parking berdasarkan ID */
  getParkingID(req, res) {
    const { place_id } = req.params;
    const { park_id } = req.params;
    pool.getConnection(
      (err, connection) => {
        if (err) throw err;
        connection.query(
          `
                SELECT * FROM parking WHERE place_id = ? AND park_id = ?;
                `,
          [place_id, park_id],
          (error, results) => {
            if (error) throw error;
            const data = [];
            let currnetPlaceId = 0;
            let currentIndex = -1;
            results.map((value) => {
              if (currnetPlaceId !== value.place_id) {
                currentIndex++;
                data[currentIndex] = {
                  place_id: value.place_id,
                };
                data[currentIndex].parking = [];
                currnetPlaceId = value.place_id;
              }
              data[currentIndex].parking.push({
                park_id: value.park_id,
                park_name: value.park_name,
                park_img: value.park_img,
                park_address: value.park_address,
                park_layout: value.park_layout, /* img = url */
                park_video: value.park_video,
              });
            });
            res.send({
              success: true,
              message: 'Berhasil ambil data!',
              hoops_entity: data,
            });
          },
        );
        connection.release();
      },
    );
  },

  /* Simpan data hoops_entity */
  addHoopsEntity(req, res) {
    const hoops_entity = {
      place_name: req.body.place_name,
      place_img: req.body.place_img,
      place_address: req.body.place_address,
    };
    pool.getConnection((err, connection) => {
      if (err) throw err;
      connection.query(
        `
                INSERT INTO hoops_entity SET ?;
                `,
        [hoops_entity],
        (error) => {
          if (error) throw error;
          res.send({
            success: true,
            message: 'Berhasil tambah data!',
          });
        },
      );
      connection.release();
    });
  },

  /* Simpan data parking */
  addParking(req, res) {
    const parking = {
      park_name: req.body.park_name,
      park_img: req.body.park_img,
      park_address: req.body.park_address,
      park_layout: req.body.park_layout,
      park_video: req.body.park_video,
      place_id: req.body.place_id,
    };
    pool.getConnection((err, connection) => {
      if (err) throw err;
      connection.query(
        `
                INSERT INTO parking SET ?;
                `,
        [parking],
        (error) => {
          if (error) throw error;
          res.send({
            success: true,
            message: 'Berhasil tambah data!',
          });
        },
      );
      connection.release();
    });
  },

  /* Update data hoops_entity */
  editHoopsEntity(req, res) {
    const hoops_entity = {
      place_name: req.body.place_name,
      place_img: req.body.place_img,
      place_address: req.body.place_address,
    };
    const { place_id } = req.params;
    pool.getConnection((err, connection) => {
      if (err) throw err;
      connection.query(
        `
                UPDATE hoops_entity SET ? WHERE place_id = ?;
                `,
        [hoops_entity, place_id],
        (error) => {
          if (error) throw error;
          res.send({
            success: true,
            message: 'Berhasil edit data!',
          });
        },
      );
      connection.release();
    });
  },

  /* Update data parking */
  editParking(req, res) {
    const parking = {
      park_name: req.body.park_name,
      park_img: req.body.park_img,
      park_address: req.body.park_address,
      park_layout: req.body.park_layout,
      park_video: req.body.park_video,
    };
    const { park_id } = req.params;
    pool.getConnection((err, connection) => {
      if (err) throw err;
      connection.query(
        `
                UPDATE parking SET ? WHERE park_id = ?;
                `,
        [parking, park_id],
        (error) => {
          if (error) throw error;
          res.send({
            success: true,
            message: 'Berhasil edit data!',
          });
        },
      );
      connection.release();
    });
  },

  /* Delete data hoops_entity */
  deleteHoopsEntityID(req, res) {
    const { place_id } = req.params;
    pool.getConnection((err, connection) => {
      if (err) throw err;
      connection.query(
        `
                DELETE FROM hoops_entity WHERE place_id = ?;
                `,
        [place_id],
        (error) => {
          if (error) throw error;
          res.send({
            success: true,
            message: 'Berhasil hapus data!',
          });
        },
      );
      connection.release();
    });
  },

  /* Delete data parking */
  deleteParkingID(req, res) {
    const { park_id } = req.params;
    pool.getConnection((err, connection) => {
      if (err) throw err;
      connection.query(
        `
                DELETE FROM parking WHERE park_id = ?;
                `,
        [park_id],
        (error) => {
          if (error) throw error;
          res.send({
            success: true,
            message: 'Berhasil hapus data!',
          });
        },
      );
      connection.release();
    });
  },

  /* Delete all data --Development Interest
    deleteAll(req,res){
        pool.getConnection(function(err, connection) {
            if (err) throw err;
            connection.query(
                `
                DELETE  FROM 'hoops_entity', 'parking';
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
    } */
};
