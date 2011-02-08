#ifndef __X10_ARRAY_UNIQUEDIST_H
#define __X10_ARRAY_UNIQUEDIST_H

#include <x10rt.h>


#define X10_ARRAY_DIST_H_NODEPS
#include <x10/array/Dist.h>
#undef X10_ARRAY_DIST_H_NODEPS
namespace x10 { namespace array { 
class PlaceGroup;
} } 
namespace x10 { namespace array { 
class Region;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class Sequence;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
class ClassCastException;
} } 
namespace x10 { namespace lang { 
class Place;
} } 
#include <x10/lang/Place.struct_h>
namespace x10 { namespace lang { 
class Runtime;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace array { 
class Point;
} } 
namespace x10 { namespace compiler { 
class CompilerFlags;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace array { 
class WrappedDistRegionRestricted;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace array { 
class WrappedDistPlaceRestricted;
} } 
namespace x10 { namespace lang { 
class Any;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterable;
} } 
namespace x10 { namespace array { 

class UniqueDist : public x10::array::Dist   {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[4];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10::lang::Place>::itable<x10::array::UniqueDist > _itable_0;
    
    static x10::lang::Any::itable<x10::array::UniqueDist > _itable_1;
    
    static x10::lang::Iterable<x10aux::ref<x10::array::Point> >::itable<x10::array::UniqueDist > _itable_2;
    
    void _instance_init();
    
    x10aux::ref<x10::array::PlaceGroup> FMGL(pg);
    
    x10aux::ref<x10::array::Region> FMGL(regionForHere);
    
    void _constructor(x10aux::ref<x10::array::PlaceGroup> g);
    
    static x10aux::ref<x10::array::UniqueDist> _make(x10aux::ref<x10::array::PlaceGroup> g);
    
    void _constructor();
    
    static x10aux::ref<x10::array::UniqueDist> _make();
    
    virtual x10aux::ref<x10::array::PlaceGroup> places();
    virtual x10_int numPlaces();
    virtual x10aux::ref<x10::lang::Iterable<x10aux::ref<x10::array::Region> > >
      regions(
      );
    virtual x10aux::ref<x10::array::Region> get(x10::lang::Place p);
    virtual x10aux::ref<x10::array::Region> __apply(x10::lang::Place p);
    virtual x10::lang::Place __apply(x10aux::ref<x10::array::Point> pt);
    virtual x10::lang::Place __apply(x10_int i0);
    virtual x10_int offset(x10aux::ref<x10::array::Point> pt);
    virtual x10_int offset(x10_int i0);
    virtual x10_int maxOffset();
    virtual x10aux::ref<x10::array::Dist> restriction(x10aux::ref<x10::array::Region> r);
    virtual x10aux::ref<x10::array::Dist> restriction(x10::lang::Place p);
    virtual x10_boolean equals(x10aux::ref<x10::lang::Any> thatObj);
    virtual x10aux::ref<x10::array::UniqueDist> x10__array__UniqueDist____x10__array__UniqueDist__this(
      );
    void __fieldInitializers1771();
    public: virtual x10::lang::Place __apply(x10_int p0, x10_int p1);
    public: virtual x10::lang::Place __apply(x10_int p0, x10_int p1, x10_int p2);
    public: virtual x10::lang::Place __apply(x10_int p0, x10_int p1, x10_int p2, x10_int p3);
    public: virtual x10_int offset(x10_int p0, x10_int p1);
    public: virtual x10_int offset(x10_int p0, x10_int p1, x10_int p2);
    public: virtual x10_int offset(x10_int p0, x10_int p1, x10_int p2, x10_int p3);
    
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
#endif // X10_ARRAY_UNIQUEDIST_H

namespace x10 { namespace array { 
class UniqueDist;
} } 

#ifndef X10_ARRAY_UNIQUEDIST_H_NODEPS
#define X10_ARRAY_UNIQUEDIST_H_NODEPS
#include <x10/array/Dist.h>
#include <x10/array/PlaceGroup.h>
#include <x10/array/Region.h>
#include <x10/lang/Int.h>
#include <x10/lang/Sequence.h>
#include <x10/array/Array.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/ClassCastException.h>
#include <x10/lang/Place.h>
#include <x10/lang/Runtime.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/array/Point.h>
#include <x10/compiler/CompilerFlags.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/array/WrappedDistRegionRestricted.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/array/WrappedDistPlaceRestricted.h>
#include <x10/lang/Any.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Iterable.h>
#ifndef X10_ARRAY_UNIQUEDIST_H_GENERICS
#define X10_ARRAY_UNIQUEDIST_H_GENERICS
template<class __T> x10aux::ref<__T> x10::array::UniqueDist::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::array::UniqueDist> this_ = new (memset(x10aux::alloc<x10::array::UniqueDist>(), 0, sizeof(x10::array::UniqueDist))) x10::array::UniqueDist();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_ARRAY_UNIQUEDIST_H_GENERICS
#endif // __X10_ARRAY_UNIQUEDIST_H_NODEPS
