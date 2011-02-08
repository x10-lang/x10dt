#ifndef __X10_ARRAY_SPARSEPLACEGROUP_H
#define __X10_ARRAY_SPARSEPLACEGROUP_H

#include <x10rt.h>


#define X10_ARRAY_PLACEGROUP_H_NODEPS
#include <x10/array/PlaceGroup.h>
#undef X10_ARRAY_PLACEGROUP_H_NODEPS
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
class Place;
} } 
#include <x10/lang/Place.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class Sequence;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
class IllegalArgumentException;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace array { 
class Point;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace array { 

class SparsePlaceGroup : public x10::array::PlaceGroup   {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[5];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static x10::lang::Sequence<x10::lang::Place>::itable<x10::array::SparsePlaceGroup > _itable_0;
    
    static x10::lang::Any::itable<x10::array::SparsePlaceGroup > _itable_1;
    
    static x10::lang::Fun_0_1<x10_int, x10::lang::Place>::itable<x10::array::SparsePlaceGroup > _itable_2;
    
    static x10::lang::Iterable<x10::lang::Place>::itable<x10::array::SparsePlaceGroup > _itable_3;
    
    void _instance_init();
    
    x10aux::ref<x10::array::Array<x10::lang::Place> > FMGL(places);
    
    void _constructor(x10aux::ref<x10::lang::Sequence<x10::lang::Place> > ps);
    
    static x10aux::ref<x10::array::SparsePlaceGroup> _make(x10aux::ref<x10::lang::Sequence<x10::lang::Place> > ps);
    
    void _constructor(x10::lang::Place p);
    
    static x10aux::ref<x10::array::SparsePlaceGroup> _make(x10::lang::Place p);
    
    virtual x10::lang::Place __apply(x10_int i);
    virtual x10aux::ref<x10::lang::Iterator<x10::lang::Place> > iterator(
      );
    virtual x10_int numPlaces();
    virtual x10_boolean contains(x10_int id);
    virtual x10_int indexOf(x10_int id);
    virtual x10aux::ref<x10::array::SparsePlaceGroup> x10__array__SparsePlaceGroup____x10__array__SparsePlaceGroup__this(
      );
    public: virtual x10_boolean contains(x10::lang::Place p0);
    public: virtual x10_int indexOf(x10::lang::Place p0);
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_ARRAY_SPARSEPLACEGROUP_H

namespace x10 { namespace array { 
class SparsePlaceGroup;
} } 

#ifndef X10_ARRAY_SPARSEPLACEGROUP_H_NODEPS
#define X10_ARRAY_SPARSEPLACEGROUP_H_NODEPS
#include <x10/array/PlaceGroup.h>
#include <x10/array/Array.h>
#include <x10/lang/Place.h>
#include <x10/lang/Sequence.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Int.h>
#include <x10/lang/IllegalArgumentException.h>
#include <x10/lang/Iterator.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Iterator.h>
#include <x10/array/Point.h>
#include <x10/lang/Iterator.h>
#ifndef X10_ARRAY_SPARSEPLACEGROUP_H_GENERICS
#define X10_ARRAY_SPARSEPLACEGROUP_H_GENERICS
template<class __T> x10aux::ref<__T> x10::array::SparsePlaceGroup::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::array::SparsePlaceGroup> this_ = new (memset(x10aux::alloc<x10::array::SparsePlaceGroup>(), 0, sizeof(x10::array::SparsePlaceGroup))) x10::array::SparsePlaceGroup();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_ARRAY_SPARSEPLACEGROUP_H_GENERICS
#endif // __X10_ARRAY_SPARSEPLACEGROUP_H_NODEPS
