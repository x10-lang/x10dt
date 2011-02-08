#ifndef __X10_ARRAY_PLACEGROUP__WORLDPLACEGROUP_H
#define __X10_ARRAY_PLACEGROUP__WORLDPLACEGROUP_H

#include <x10rt.h>


#define X10_ARRAY_PLACEGROUP_H_NODEPS
#include <x10/array/PlaceGroup.h>
#undef X10_ARRAY_PLACEGROUP_H_NODEPS
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
class Place;
} } 
#include <x10/lang/Place.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 
class Any;
} } 
namespace x10 { namespace array { 

class PlaceGroup__WorldPlaceGroup : public x10::array::PlaceGroup   {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[5];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static x10::lang::Sequence<x10::lang::Place>::itable<x10::array::PlaceGroup__WorldPlaceGroup > _itable_0;
    
    static x10::lang::Any::itable<x10::array::PlaceGroup__WorldPlaceGroup > _itable_1;
    
    static x10::lang::Fun_0_1<x10_int, x10::lang::Place>::itable<x10::array::PlaceGroup__WorldPlaceGroup > _itable_2;
    
    static x10::lang::Iterable<x10::lang::Place>::itable<x10::array::PlaceGroup__WorldPlaceGroup > _itable_3;
    
    void _instance_init();
    
    virtual x10::lang::Place __apply(x10_int i);
    virtual x10aux::ref<x10::lang::Iterator<x10::lang::Place> > iterator(
      );
    virtual x10_int numPlaces();
    virtual x10_boolean contains(x10_int id);
    virtual x10_int indexOf(x10_int id);
    virtual x10_boolean equals(x10aux::ref<x10::lang::Any> thatObj);
    virtual x10_int hashCode();
    virtual x10aux::ref<x10::array::PlaceGroup__WorldPlaceGroup> x10__array__PlaceGroup__WorldPlaceGroup____x10__array__PlaceGroup__WorldPlaceGroup__this(
      );
    void _constructor();
    
    static x10aux::ref<x10::array::PlaceGroup__WorldPlaceGroup> _make(
             );
    
    public: virtual x10_boolean contains(x10::lang::Place p0);
    public: virtual x10_int indexOf(x10::lang::Place p0);
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_ARRAY_PLACEGROUP__WORLDPLACEGROUP_H

namespace x10 { namespace array { 
class PlaceGroup__WorldPlaceGroup;
} } 

#ifndef X10_ARRAY_PLACEGROUP__WORLDPLACEGROUP_H_NODEPS
#define X10_ARRAY_PLACEGROUP__WORLDPLACEGROUP_H_NODEPS
#include <x10/array/PlaceGroup.h>
#include <x10/lang/Int.h>
#include <x10/lang/Place.h>
#include <x10/lang/Iterator.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Any.h>
#ifndef X10_ARRAY_PLACEGROUP__WORLDPLACEGROUP_H_GENERICS
#define X10_ARRAY_PLACEGROUP__WORLDPLACEGROUP_H_GENERICS
template<class __T> x10aux::ref<__T> x10::array::PlaceGroup__WorldPlaceGroup::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::array::PlaceGroup__WorldPlaceGroup> this_ = new (memset(x10aux::alloc<x10::array::PlaceGroup__WorldPlaceGroup>(), 0, sizeof(x10::array::PlaceGroup__WorldPlaceGroup))) x10::array::PlaceGroup__WorldPlaceGroup();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_ARRAY_PLACEGROUP__WORLDPLACEGROUP_H_GENERICS
#endif // __X10_ARRAY_PLACEGROUP__WORLDPLACEGROUP_H_NODEPS
