@org.hibernate.annotations.GenericGenerator(
  name = "ID_GENERATOR",
  strategy = "enhanced-sequence",
  parameters = {
     @org.hibernate.annotations.Parameter(
        name = "sequence_name",
        value = "JPWH_SEQUENCE"
     ),
     @org.hibernate.annotations.Parameter(
        name = "initial_value",
        value = "1000"
     )
})
package com.wesdm.springhibernate.model;

//@org.hibernate.annotations.GenericGenerator(name = "sequence_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
//@org.hibernate.annotations.Parameter(name = "sequence_name", value = "hilo_seqeunce"), @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
//@org.hibernate.annotations.Parameter(name = "increment_size", value = "3"), @org.hibernate.annotations.Parameter(name = "optimizer", value = "hilo") })
