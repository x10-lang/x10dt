#ifndef __X10_ARRAY_PLACEGROUP_H
#define __X10_ARRAY_PLACEGROUP_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_LANG_SEQUENCE_H_NODEPS
#include <x10/lang/Sequence.h>
#undef X10_LANG_SEQUENCE_H_NODEPS
namespace x10 { namespace lang { 
class Place;
} } 
namespace x10 { namespace array { 
class PlaceGroup__WorldPlaceGroup;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 
class Any;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace array { 

class PlaceGroup : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    static x10aux::ref<x10::array::PlaceGroup__WorldPlaceGroup> FMGL(WORLD);
    
    static void FMGL(WORLD__do_init)();
    static void FMGL(WORLD__init)();
    static volatile x10aux::status FMGL(WORLD__status);
    static inline x10aux::ref<x10::array::PlaceGroup__WorldPlaceGroup> FMGL(WORLD__get)() {
        if (FMGL(WORLD__status) != x10aux::INITIALIZED) {
            FMGL(WORLD__init)();
        }
        return x10::array::PlaceGroup::FMGL(WORLD);
    }
    static x10aux::ref<x10::lang::Reference> FMGL(WORLD__deserialize)(x10aux::deserialization_buffer &buf);
    static const x10aux::serialization_id_t FMGL(WORLD__id);
    
    x10_int size();
    virtual x10_int numPlaces() = 0;
    virtual x10_boolean contains(x10::lang::Place place);
    virtual x10_boolean contains(x10_int id) = 0;
    virtual x10_int indexOf(x10::lang::Place place);
    virtual x10_int indexOf(x10_int id) = 0;
    virtual x10::lang::Place __apply(x10_int i) = 0;
    virtual x10_boolean equals(x10aux::ref<x10::lang::Any> thatObj);
    virtual x10aux::ref<x10::array::PlaceGroup> x10__array__PlaceGroup____x10__array__PlaceGroup__this(
      );
    void _constructor();
    
    virtual x10aux::ref<x10::lang::Iterator<x10::lang::Place> > iterator(
      ) = 0;
    
    // Serialization
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_ARRAY_PLACEGROUP_H

namespace x10 { namespace array { 
class PlaceGroup;
} } 

#ifndef X10_ARRAY_PLACEGROUP_H_NODEPS
#define X10_ARRAY_PLACEGROUP_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Sequence.h>
#include <x10/lang/Place.h>
#include <x10/array/PlaceGroup__WorldPlaceGroup.h>
#include <x10/lang/Int.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Any.h>
#include <x10/lang/Iterator.h>
#ifndef X10_ARRAY_PLACEGROUP_H_GENERICS
#define X10_ARRAY_PLACEGROUP_H_GENERICS
#endif // X10_ARRAY_PLACEGROUP_H_GENERICS
#endif // __X10_ARRAY_PLACEGROUP_H_NODEPS
