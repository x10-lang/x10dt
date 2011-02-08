#ifndef __X10_ARRAY_DIST_H
#define __X10_ARRAY_DIST_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_LANG_FUN_0_1_H_NODEPS
#include <x10/lang/Fun_0_1.h>
#undef X10_LANG_FUN_0_1_H_NODEPS
namespace x10 { namespace array { 
class Point;
} } 
namespace x10 { namespace lang { 
class Place;
} } 
#define X10_LANG_ITERABLE_H_NODEPS
#include <x10/lang/Iterable.h>
#undef X10_LANG_ITERABLE_H_NODEPS
namespace x10 { namespace array { 
class Point;
} } 
namespace x10 { namespace array { 
class Region;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace array { 
class UniqueDist;
} } 
namespace x10 { namespace array { 
class ConstantDist;
} } 
namespace x10 { namespace lang { 
class Runtime;
} } 
namespace x10 { namespace array { 
class PlaceGroup;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
class ClassCastException;
} } 
namespace x10 { namespace array { 
class BlockBlockDist;
} } 
namespace x10 { namespace lang { 
class UnsupportedOperationException;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace array { 
class BlockDist;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterable;
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
namespace x10 { namespace lang { 
class Any;
} } 
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace lang { 
class ArrayIndexOutOfBoundsException;
} } 
namespace x10 { namespace compiler { 
class NoInline;
} } 
namespace x10 { namespace compiler { 
class NoReturn;
} } 
namespace x10 { namespace lang { 
class BadPlaceException;
} } 
namespace x10 { namespace array { 

class Dist : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    x10aux::ref<x10::array::Region> FMGL(region);
    
    void _instance_init();
    
    x10_int rank();
    static x10aux::ref<x10::array::Dist> makeUnique();
    static x10aux::ref<x10::array::UniqueDist> FMGL(UNIQUE);
    
    static void FMGL(UNIQUE__do_init)();
    static void FMGL(UNIQUE__init)();
    static volatile x10aux::status FMGL(UNIQUE__status);
    static inline x10aux::ref<x10::array::UniqueDist> FMGL(UNIQUE__get)() {
        if (FMGL(UNIQUE__status) != x10aux::INITIALIZED) {
            FMGL(UNIQUE__init)();
        }
        return x10::array::Dist::FMGL(UNIQUE);
    }
    static x10aux::ref<x10::lang::Reference> FMGL(UNIQUE__deserialize)(x10aux::deserialization_buffer &buf);
    static const x10aux::serialization_id_t FMGL(UNIQUE__id);
    
    static x10aux::ref<x10::array::Dist> makeConstant(x10aux::ref<x10::array::Region> r);
    static x10aux::ref<x10::array::Dist> make(x10aux::ref<x10::array::Region> r);
    static x10aux::ref<x10::array::Dist> makeCyclic(x10aux::ref<x10::array::Region> r,
                                                    x10_int axis);
    static x10aux::ref<x10::array::Dist> makeBlock(x10aux::ref<x10::array::Region> r,
                                                   x10_int axis);
    static x10aux::ref<x10::array::Dist> makeBlockBlock(x10aux::ref<x10::array::Region> r,
                                                        x10_int axis0,
                                                        x10_int axis1);
    static x10aux::ref<x10::array::Dist> makeBlock(
      x10aux::ref<x10::array::Region> r);
    static x10aux::ref<x10::array::Dist> makeBlockCyclic(
      x10aux::ref<x10::array::Region> r,
      x10_int axis,
      x10_int blockSize);
    static x10aux::ref<x10::array::Dist> makeUnique(
      x10aux::ref<x10::array::PlaceGroup> pg);
    static x10aux::ref<x10::array::Dist> makeConstant(
      x10aux::ref<x10::array::Region> r,
      x10::lang::Place p);
    static x10aux::ref<x10::array::Dist> makeCyclic(
      x10aux::ref<x10::array::Region> r,
      x10_int axis,
      x10aux::ref<x10::array::PlaceGroup> pg);
    static x10aux::ref<x10::array::Dist> makeBlock(
      x10aux::ref<x10::array::Region> r,
      x10_int axis,
      x10aux::ref<x10::array::PlaceGroup> pg);
    static void makeBlockCyclic(x10aux::ref<x10::array::Region> r,
                                x10_int axis,
                                x10_int blockSize,
                                x10aux::ref<x10::array::PlaceGroup> pg);
    virtual x10aux::ref<x10::array::PlaceGroup> places(
      ) = 0;
    virtual x10_int numPlaces() = 0;
    virtual x10aux::ref<x10::lang::Iterable<x10aux::ref<x10::array::Region> > >
      regions(
      ) = 0;
    virtual x10aux::ref<x10::array::Region> get(x10::lang::Place p) = 0;
    virtual x10aux::ref<x10::array::Region> __apply(
      x10::lang::Place p);
    virtual x10::lang::Place __apply(x10aux::ref<x10::array::Point> pt) = 0;
    virtual x10::lang::Place __apply(x10_int i0);
    virtual x10::lang::Place __apply(x10_int i0, x10_int i1);
    virtual x10::lang::Place __apply(x10_int i0, x10_int i1,
                                     x10_int i2);
    virtual x10::lang::Place __apply(x10_int i0, x10_int i1,
                                     x10_int i2,
                                     x10_int i3);
    virtual x10_int offset(x10aux::ref<x10::array::Point> pt) = 0;
    virtual x10_int offset(x10_int i0);
    virtual x10_int offset(x10_int i0, x10_int i1);
    virtual x10_int offset(x10_int i0, x10_int i1,
                           x10_int i2);
    virtual x10_int offset(x10_int i0, x10_int i1,
                           x10_int i2,
                           x10_int i3);
    virtual x10_int maxOffset() = 0;
    virtual x10aux::ref<x10::lang::Iterator<x10aux::ref<x10::array::Point> > >
      iterator(
      );
    virtual x10aux::ref<x10::array::Dist> restriction(
      x10aux::ref<x10::array::Region> r) = 0;
    virtual x10_boolean isSubdistribution(x10aux::ref<x10::array::Dist> that);
    virtual x10_boolean equals(x10aux::ref<x10::lang::Any> thatObj);
    virtual x10aux::ref<x10::array::Dist> restriction(
      x10::lang::Place p) = 0;
    virtual x10_boolean contains(x10aux::ref<x10::array::Point> p);
    virtual x10aux::ref<x10::array::Dist> __bar(x10aux::ref<x10::array::Region> r);
    virtual x10aux::ref<x10::array::Dist> __bar(x10::lang::Place p);
    virtual x10aux::ref<x10::lang::String> toString(
      );
    void _constructor(x10aux::ref<x10::array::Region> region);
    
    static void raiseBoundsError(x10_int i0) X10_PRAGMA_NORETURN ;
    static void raiseBoundsError(x10_int i0, x10_int i1) X10_PRAGMA_NORETURN ;
    static void raiseBoundsError(x10_int i0, x10_int i1,
                                 x10_int i2) X10_PRAGMA_NORETURN ;
    static void raiseBoundsError(x10_int i0, x10_int i1,
                                 x10_int i2,
                                 x10_int i3) X10_PRAGMA_NORETURN ;
    static void raiseBoundsError(x10aux::ref<x10::array::Point> pt) X10_PRAGMA_NORETURN ;
    static void raisePlaceError(x10_int i0) X10_PRAGMA_NORETURN ;
    static void raisePlaceError(x10_int i0, x10_int i1) X10_PRAGMA_NORETURN ;
    static void raisePlaceError(x10_int i0, x10_int i1,
                                x10_int i2) X10_PRAGMA_NORETURN ;
    static void raisePlaceError(x10_int i0, x10_int i1,
                                x10_int i2,
                                x10_int i3) X10_PRAGMA_NORETURN ;
    static void raisePlaceError(x10aux::ref<x10::array::Point> pt) X10_PRAGMA_NORETURN ;
    x10aux::ref<x10::array::Region> region();
    virtual x10aux::ref<x10::array::Dist> x10__array__Dist____x10__array__Dist__this(
      );
    
    // Serialization
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_ARRAY_DIST_H

namespace x10 { namespace array { 
class Dist;
} } 

#ifndef X10_ARRAY_DIST_H_NODEPS
#define X10_ARRAY_DIST_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/array/Point.h>
#include <x10/lang/Place.h>
#include <x10/lang/Iterable.h>
#include <x10/array/Point.h>
#include <x10/array/Region.h>
#include <x10/lang/Int.h>
#include <x10/array/UniqueDist.h>
#include <x10/array/ConstantDist.h>
#include <x10/lang/Runtime.h>
#include <x10/array/PlaceGroup.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/ClassCastException.h>
#include <x10/array/BlockBlockDist.h>
#include <x10/lang/UnsupportedOperationException.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/array/BlockDist.h>
#include <x10/lang/Iterable.h>
#include <x10/lang/Iterator.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Iterator.h>
#include <x10/lang/Any.h>
#include <x10/lang/String.h>
#include <x10/lang/ArrayIndexOutOfBoundsException.h>
#include <x10/compiler/NoInline.h>
#include <x10/compiler/NoReturn.h>
#include <x10/lang/BadPlaceException.h>
#ifndef X10_ARRAY_DIST_H_GENERICS
#define X10_ARRAY_DIST_H_GENERICS
#endif // X10_ARRAY_DIST_H_GENERICS
#endif // __X10_ARRAY_DIST_H_NODEPS
